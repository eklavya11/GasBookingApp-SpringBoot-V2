package com.egasbooking.app.repository;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.egasbooking.app.entity.GasBooking;
 
@Repository
public interface GasBookingRepository extends JpaRepository<GasBooking, Integer> {
	 @Query(value = "SELECT * FROM gas_booking WHERE customer_fk=:customerId", nativeQuery = true)
    public List<GasBooking> findByCustomerId(int customerId);
 
    @Query(value = "SELECT bill FROM gas_booking WHERE customer_fk=:customerId", nativeQuery = true)
    public float getBillByCustomerId(int customerId);
 
    @Query(value = "SELECT g from GasBooking g where g.customer.userId=?1")
    public List<GasBooking> getAllBookingByCustomerId(int customerId);
 
    @Query(value = "SELECT * FROM gas_booking g "
			+ " WHERE  g.booking_date=:bookingDate ", nativeQuery = true)
    public List<GasBooking> findByBookingDate(LocalDate bookingDate);
 

}

