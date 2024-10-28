package com.example.movie.controller;

import com.example.movie.domain.BoxOffice;
import com.example.movie.domain.Reservation;
import com.example.movie.repository.BoxOfficeRepository;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.service.BoxOfficeService;
import com.example.movie.service.ReservationService;
import com.example.movie.user.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final BoxOfficeService boxOfficeService;
    @Autowired
    private BoxOfficeRepository boxOfficeRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    //의존성 주입(생성자를 사용하여 멤버변수 초기화)

    public ReservationController(
            ReservationService reservationService,
            BoxOfficeService boxOfficeService
    ){
        this.reservationService = reservationService;
        this.boxOfficeService = boxOfficeService;
    }
    //사용자 인증(로그인 정보)
    @GetMapping(value = "/isAuthenticated")
    @ResponseBody
    public boolean isAuthenticated(Authentication authentication){
        return authentication != null && authentication.isAuthenticated();
    }

    //ResponseBody로 데이터 전달하면 RequestParam으로 데이터를 전달받음
    @GetMapping("/start")
    public String startGET(
            @RequestParam("rank") Long rank,
            @RequestParam("movieNm") String movieNm,
            @RequestParam("image") String image,
            @RequestParam("overview") String overview,
            @RequestParam("runtime") String runtime,
            @RequestParam("releaseDate") String releaseDate,
            @RequestParam("reservation_num") String reservation_num,
            Model model,
            Customer customer,
            HttpServletRequest request
    ){
        //영화이름으로 좌석예매 정보 검색
        List<String> reservedSeats = reservationRepository.findReservationSeats(movieNm);
        //로그인 된 사용자 인증정보 가져오기
        Object principal = request.getUserPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("userName", username);

        //모델 객체를 사용하여 영화 관련 정보 저장
        model.addAttribute("rank", rank);
        model.addAttribute("movieNm", movieNm);
        model.addAttribute("image", image);
        model.addAttribute("overview", overview);
        model.addAttribute("runtime", runtime);
        model.addAttribute("releaseDate", releaseDate);
        model.addAttribute("reservedSeats", reservedSeats);
        return "reserve/start";
    }

    @PostMapping("/start")
    public String startPOST(HttpServletRequest httpServletRequest, Model model, RedirectAttributes redirectAttrs){
        Long rank = Long.valueOf(httpServletRequest.getParameter("rank"));
        String reservation_day = (httpServletRequest.getParameter("reservation_day") != null) ? httpServletRequest.getParameter("reservation_day") : "";
        String reservation_num = (httpServletRequest.getParameter("reservation_num") != null) ? httpServletRequest.getParameter("reservation_num") : "";
        String movieNm = httpServletRequest.getParameter("movieNm");
        String image = httpServletRequest.getParameter("image");
        String overview = httpServletRequest.getParameter("overview");
        String runtime = httpServletRequest.getParameter("runtime");
        String releaseDate = httpServletRequest.getParameter("releaseDate");
        List<String> reservedSeats = reservationRepository.findReservationSeats(reservation_num);

        model.addAttribute("rank", rank);
        model.addAttribute("movieNm", movieNm);
        model.addAttribute("image", image);
        model.addAttribute("overview", overview);
        model.addAttribute("runtime", runtime);
        model.addAttribute("releaseDate", releaseDate);
        model.addAttribute("reservedSeats", reservedSeats);

        //리다이렉트 URL에 쿼리 파라미터로 값을 전달하기 위해 RedirectAttributes에 추가
        redirectAttrs.addAttribute("rank", rank);
        redirectAttrs.addAttribute("reservation_day", reservation_day);
        redirectAttrs.addAttribute("reservation_num", reservation_num);
        redirectAttrs.addAttribute("movieNm", movieNm);
        redirectAttrs.addAttribute("image", image);
        redirectAttrs.addAttribute("overview", overview);
        redirectAttrs.addAttribute("runtime", runtime);
        redirectAttrs.addAttribute("releaseDate", releaseDate);

        return "redirect:/reserve/start";
    }
    //예매 페이지에서 예매하기 버튼을 클릭하면 예매내역 점검
    @GetMapping("/check")
    public String checkGET(
        @RequestParam("rank") Long rank,
        @RequestParam("movieNm") String movieNm,
        @RequestParam("image") String image,
        @RequestParam("reservation_day") String reservation_day,
        @RequestParam("reservation_num") String reservation_num,
        Model model
    ) {
        model.addAttribute("rank", rank);
        model.addAttribute("movieNm", movieNm);
        model.addAttribute("image", image);
        model.addAttribute("reservation_day", reservation_day);
        model.addAttribute("reservation_num", reservation_num);
        return "reserve/check";
    }
    @PostMapping("/check")
    public String checkPOST(
            @RequestParam("rank") Long rank,
            @RequestParam("movieNm") String movieNm,
            @RequestParam("image") String image,
            @RequestParam("reservation_day") String reservation_day,
            @RequestParam("reservation_num") String reservation_num,
            Model model
    ) {
        model.addAttribute("rank", rank);
        model.addAttribute("movieNm", movieNm);
        model.addAttribute("image", image);
        model.addAttribute("reservation_day", reservation_day);
        model.addAttribute("reservation_num", reservation_num);
        return "reserve/check";
    }
    //예매내역을 reservation 테이블에 데이터로 저장
    @GetMapping("/done")
    public String doneGET(
        @RequestParam("username") String username,
        @RequestParam("rank") Long rank,
        @RequestParam("movieNm") String movieNm,
        @RequestParam("image") String image,
        @RequestParam("reservation_day") String reservation_day,
        @RequestParam("reservation_num") String reservation_num
    ){
        Reservation reservation = new Reservation();
        reservation.setUserName(username);
        reservation.setReservation_day(reservation_day);
        reservation.setReservation_num(reservation_num);
        reservation.setRank(rank);
        reservation.setMovieNm(movieNm);
        reservation.setImage(image);
        Reservation saveReserve = reservationService.save(reservation);
        return "reserve/done";
    }
    @PostMapping("/done")
    public String donePOST(
            @RequestParam("username") String username,
            @RequestParam("rank") Long rank,
            @RequestParam("movieNm") String movieNm,
            @RequestParam("image") String image,
            @RequestParam("reservation_day") String reservation_day,
            @RequestParam("reservation_num") String reservation_num
    ){
        Reservation reservation = new Reservation();
        reservation.setUserName(username);
        reservation.setReservation_day(reservation_day);
        reservation.setReservation_num(reservation_num);
        reservation.setRank(rank);
        reservation.setMovieNm(movieNm);
        reservation.setImage(image);
        Reservation saveReserve = reservationService.save(reservation);
        return "reserve/done";
    }

    @GetMapping("/quick")
    public String boxOfficeList(Model model, HttpServletRequest request){
        List<BoxOffice> boxOfficeList = this.boxOfficeRepository.findAll();
        //로그인 된 사용자 인증정보 가져오기
        Object principal = request.getUserPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("username", username);
        model.addAttribute("boxOfficeList", boxOfficeList);
        return "reserve/quick";
    }
}
