/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data;

import lk.gov.health.entity.Staff;
import lk.gov.health.entity.WebUser;
import java.util.Date;

/**
 *
 * @author buddhika_ari
 */
public class DoctorDayChannelCount {

    private Staff staff;
    private Date appointmentDate;
    private Long booked;
    private Long cancelled;
    private Long refunded;
    
    public DoctorDayChannelCount() {
    }

    public DoctorDayChannelCount(Staff staff, Date appointmentDate, Long booked, Long cancelled, Long refunded) {
        this.staff = staff;
        this.appointmentDate = appointmentDate;
        this.booked = booked;
        this.cancelled = cancelled;
        this.refunded = refunded;
    }

    public DoctorDayChannelCount(Staff staff, Date appointmentDate, Long booked) {
        this.staff = staff;
        this.appointmentDate = appointmentDate;
        this.booked = booked;
    }
    
    
    
    

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Long getBooked() {
        return booked;
    }

    public void setBooked(Long booked) {
        this.booked = booked;
    }

    public Long getCancelled() {
        return cancelled;
    }

    public void setCancelled(Long cancelled) {
        this.cancelled = cancelled;
    }

    public Long getRefunded() {
        return refunded;
    }

    public void setRefunded(Long refunded) {
        this.refunded = refunded;
    }

    
    
    
    
    
}
