package com.graduation.PublicBicycle.service;

import com.graduation.PublicBicycle.dto.ChitddDTO;
import com.graduation.PublicBicycle.dto.DonDatDTO;
import com.graduation.PublicBicycle.dto.UserDTO;
import com.graduation.PublicBicycle.dto.XeDapDTO;
import com.graduation.PublicBicycle.entity.*;
import com.graduation.PublicBicycle.repository.*;
import com.graduation.PublicBicycle.request.BookRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class XeDapService {

    @Autowired
    private XeDapRepository xeDapRepository;

    @Autowired
    private TramRepository tramRepository;

    @Autowired
    private ChitddRepository chitddRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonDatRepository donDatRepository;

    private static int donDatCounter = 1; // Biến đếm cho mã đơn đặt


    public List<XeDapDTO> getAll(){
        List<XeDap> listXe = xeDapRepository.findAll();
        List<XeDapDTO> listXeTO = new ArrayList<>();
        for (XeDap data : listXe){
            XeDapDTO xeDTO = new XeDapDTO();
            BeanUtils.copyProperties(data, xeDTO);
            listXeTO.add(xeDTO);
        }
        return listXeTO;
    }

    public List<XeDapDTO> getAllByTram(String matram) {
        List<XeDap> listXe = xeDapRepository.findByTram(matram);
        List<XeDapDTO> listXeTO = new ArrayList<>();
        for (XeDap data : listXe){
            XeDapDTO xeDTO = new XeDapDTO();
            BeanUtils.copyProperties(data, xeDTO);
            listXeTO.add(xeDTO);
        }
        return listXeTO;
    }

    public DonDat taoDonDat(BookRequest request, String username) {
        Tram tram = tramRepository.findByTram(request.getMatram());
        if (tram == null) {
            throw new IllegalArgumentException("Không tìm thấy trạm đỗ xe có mã " + request.getMatram());
        }

        String maDonDat = generateDonDatID();
        DonDat donDat = new DonDat();
        donDat.setMadondat(maDonDat);
        donDat.setSoluongdd(request.getSoluong());
        donDat.setTrangthaidd(true);

        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy người dùng có tên đăng nhập " + username);
        }

        donDat.setMaUser(user);

        return donDatRepository.save(donDat);
    }

    public void taoChiTietDatXe(DonDat donDat, Tram tram, int soLuongXe) {
        List<XeDap> xeDapList = xeDapRepository.findByTram(tram.getMatram());
        int demXeDat = 0;
        for (XeDap xeDap : xeDapList) {
            if (!xeDap.isTrangthaixe()) {
                xeDap.setTrangthaixe(true);
                demXeDat++;
                if (demXeDat > soLuongXe) {
                    break;
                }
                Tram tramTra = tramRepository.findByTram("TRAM0095");
                Chitdd chitiet = new Chitdd();
                chitiet.setMadondat(donDat);
                chitiet.setNgaygiomuon(LocalDateTime.now());
                chitiet.setTrammuon(tram);
                chitiet.setTramtra(tramTra);
                chitiet.setImeixe(xeDap);
                chitddRepository.save(chitiet);
            }
        }
    }

    private synchronized String generateDonDatID() {
        String formattedCounter = String.format("%04d", donDatCounter++); // Format biến đếm thành chuỗi với độ dài là 4 và đệm bằng số 0
        return "DD" + formattedCounter;
    }

    public XeDapDTO addXe(XeDapDTO xeDapDTO) {
        if (xeDapDTO == null) {
            return null;
        }
        XeDap exist = xeDapRepository.findByImeixe(xeDapDTO.getImeixe());
        if (exist != null) {
            return null;
        }
        String tramId = xeDapDTO.getMatram().getMatram();
        Optional<Tram> tramOptional = Optional.ofNullable(tramRepository.findByTram(tramId));
        if (tramOptional.isEmpty()) {
            throw new RuntimeException("Tram with ID " + tramId + " does not exist.");
        }
        Tram tram = tramOptional.get();
        XeDap xedap = new XeDap();
        BeanUtils.copyProperties(xeDapDTO, xedap);

        xedap.setTrangthaixe(false);
        xedap.setMatram(tram);

        XeDap saved = xeDapRepository.save(xedap);
        XeDapDTO savedDTO = new XeDapDTO();
        BeanUtils.copyProperties(saved, savedDTO);
        return savedDTO;
    }


    public XeDapDTO updateXe(String imeixe, XeDapDTO xeDapDTO) {
        XeDap xeDap = xeDapRepository.findByImeixe(imeixe);
        if (xeDap == null) {
            return null;
        }
        String tramId = xeDapDTO.getMatram().getMatram();
        Optional<Tram> tramOptional = Optional.ofNullable(tramRepository.findByTram(tramId));
        if (tramOptional.isEmpty()) {
            throw new RuntimeException("Tram with ID " + tramId + " does not exist.");
        }
        Tram tram = tramOptional.get();
        BeanUtils.copyProperties(xeDapDTO, xeDap);
        xeDap.setMatram(tram);
        xeDap.setTrangthaixe(xeDap.isTrangthaixe());

        XeDap updated = xeDapRepository.save(xeDap);
        XeDapDTO updatedDTO = new XeDapDTO();
        BeanUtils.copyProperties(updated, updatedDTO);
        return updatedDTO;
    }


    public void deleteXe(String imeixe) {
        XeDap xeDap = xeDapRepository.findByImeixe(imeixe);
        if (xeDap != null) {
            xeDapRepository.delete(xeDap);
        } else {
            throw new RuntimeException("Xe with IMEI " + imeixe + " does not exist.");
        }
    }

    public List<DonDatDTO> getDanhSachHoaDonCanThanhToan(String username) {
        List<Object[]> results = donDatRepository.findDonNeedThanhToan(username);
        List<DonDatDTO> danhSachDonDatDTO = new ArrayList<>();

        for (Object[] result : results) {
            DonDatDTO donDatDTO = convertToDTO(result);
            danhSachDonDatDTO.add(donDatDTO);
        }

        return danhSachDonDatDTO;
    }

    private DonDatDTO convertToDTO(Object[] result) {
        DonDatDTO donDatDTO = new DonDatDTO();
        donDatDTO.setMadondat((String) result[0]);
        donDatDTO.setSoluongdd((Integer) result[1]);
        donDatDTO.setTrangthaidd((Boolean) result[2]);
        donDatDTO.setSotien((Integer) result[3]);

        UserDTO userDTO = new UserDTO();
        userDTO.setId((Integer) result[11]);
        userDTO.setFirstname((String) result[12]);
        userDTO.setLastname((String) result[13]);
        userDTO.setUsername((String) result[14]);
        userDTO.setSdt((String) result[15]);
        userDTO.setPassword((String) result[16]);
        userDTO.setRole((String) result[17]);
        userDTO.setSodu((Integer) result[18]);
        donDatDTO.setMaUser(userDTO);

        List<ChitddDTO> chitdds = new ArrayList<>();
        ChitddDTO chitddDTO = new ChitddDTO();
        chitddDTO.setNgaygiomuon((LocalDateTime) result[4]);
        chitddDTO.setNgaygiotra((LocalDateTime) result[5]);
        chitddDTO.setTrammuon((Tram) result[6]);
        chitddDTO.setTramtra((Tram) result[7]);
        chitddDTO.setImeixe((XeDap) result[8]);
        chitddDTO.setThanhtoan((Double) result[9]);
        chitddDTO.setTrangthai((Boolean) result[10]);

        chitdds.add(chitddDTO);
        donDatDTO.setChitdds(chitdds);
        return donDatDTO;
    }

    public void traXe(String username, String maTramTra) {
        // Tìm đơn đặt theo tên người dùng
        List<Object[]> donDatList = donDatRepository.findDonNeedThanhToan(username);

        if (donDatList.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy đơn đặt cần thanh toán cho người dùng " + username);
        }

        // Chuyển đổi kết quả đầu tiên thành DonDatDTO
        DonDatDTO donDatDTO = convertToDTO(donDatList.get(0));

        // Tìm trạm trả theo mã trạm
        Tram tramTra = tramRepository.findByTram(maTramTra);
        if (tramTra == null) {
            throw new IllegalArgumentException("Không tìm thấy trạm đỗ xe có mã " + maTramTra);
        }

        // Lấy mã đơn đặt từ DonDatDTO
        String maDonDat = donDatDTO.getMadondat();

        // Lấy danh sách chi tiết đơn đặt theo mã đơn đặt
        List<Chitdd> chiTietDonDats = chitddRepository.findByIdDonDat(maDonDat);

        // Cập nhật thông tin cho từng chi tiết đơn đặt
        for (Chitdd chitdd : chiTietDonDats) {
            // Cập nhật thông tin ngày giờ trả
            chitdd.setNgaygiotra(LocalDateTime.now());
            // Cập nhật thông tin trạm trả
            chitdd.setTramtra(tramTra);
            // Cập nhật trạng thái của xe đạp
            XeDap xeDap = chitdd.getImeixe();
            xeDap.setTrangthaixe(false);
            xeDap.setMatram(tramTra);
            xeDapRepository.save(xeDap);
            // Cập nhật thông tin thanh toán nếu cần thiết (tính toán tiền thuê xe)
            chitdd.setThanhtoan(tinhTienThue(chitdd.getNgaygiomuon(), chitdd.getNgaygiotra()));
            chitdd.setTrangthai(true);
            chitddRepository.save(chitdd);
        }

        // Cập nhật trạng thái đơn đặt
        DonDat donDat = new DonDat();
        BeanUtils.copyProperties(donDatDTO, donDat);
        donDat.setTrangthaidd(false);
        donDatRepository.save(donDat);
    }

    private double tinhTienThue(LocalDateTime ngayGioMuon, LocalDateTime ngayGioTra) {
        // Giả sử tính tiền thuê xe dựa trên thời gian thuê
        long minutes = java.time.Duration.between(ngayGioMuon, ngayGioTra).toMinutes();
        double pricePerMinute = 1.0; // Ví dụ: 1 đơn vị tiền tệ mỗi phút
        return minutes * pricePerMinute;
    }
}
