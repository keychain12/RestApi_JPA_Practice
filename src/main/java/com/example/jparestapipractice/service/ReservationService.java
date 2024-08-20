package com.example.jparestapipractice.service;

import com.example.jparestapipractice.domain.*;
import com.example.jparestapipractice.dto.payment.request.PaymentInfo;
import com.example.jparestapipractice.repository.FlightRepository;
import com.example.jparestapipractice.repository.PaymentRepository;
import com.example.jparestapipractice.repository.ReservationRepository;
import com.example.jparestapipractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PaymentRepository paymentRepository;
    @Transactional(readOnly = true)
    public Reservation getReservation(Long reservationId) {
      return  reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException(" 예약 없어"));
    }

    public Long addReservation(Long userId, Long flightId, Reservation reservation, PaymentInfo paymentInfo) {
        // 회원 , 항공편 찾기
        User user = findUser(userId);
        Flight flight = findFlight(flightId);

        // 찾은 회원 ,항공편 예약에 넣기 / 연관메서드사용
        reservation.setUser(user);
        reservation.setFlight(flight);
        // 저장하기
        Reservation save = reservationRepository.save(reservation);

        //결제 처리
        boolean paymentSuccess = true;
        if (paymentSuccess) {
            // 결제 상태 확인
            Payment payment = Payment.builder()
                    .amount(paymentInfo.getAmount())
                    .paymentMethod(paymentInfo.getPaymentMethod())
                    .status(PaymentStatus.PAID)
                    .reservation(save)
                    .build();

            //저장
            paymentRepository.save(payment);

        } else {
            throw new IllegalArgumentException("결제 실패");
        }

        // 아이디 값 리턴
        return save.getId();
    }

    private Flight findFlight(Long flightId) { // 항공편 찾기
        return flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("항공편 없음"));
    }


    private User findUser(Long userId) { // 회원 찾기
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원없음"));
        return user;
    }


    public void cancel(Long reservationId) {
        Reservation reservation = getReservation(reservationId);
        reservation.cancel();
    }

}
