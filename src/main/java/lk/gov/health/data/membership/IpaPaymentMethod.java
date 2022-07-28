/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.membership;

import lk.gov.health.data.PaymentMethod;
import lk.gov.health.entity.PriceMatrix;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safrin
 */
public class IpaPaymentMethod {

    PaymentMethod paymentMethod;
    List<PriceMatrix> priceMatrixs;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<PriceMatrix> getPriceMatrixs() {
        if (priceMatrixs == null) {
            priceMatrixs = new ArrayList<>();
        }
        return priceMatrixs;
    }

    public void setPriceMatrixs(List<PriceMatrix> priceMatrixs) {
        this.priceMatrixs = priceMatrixs;
    }

}
