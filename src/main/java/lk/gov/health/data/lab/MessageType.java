/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.lab;

/**
 *
 * @author Dr. M H B Ariyaratne <buddhika.ari@gmail.com>
 */
public enum MessageType {
    Poll,
    SampleRequest,
    NoRequest,
    RequestAcceptance,
    QueryMessage,
    EnhancedQueryMessage,
    ResultAcceptance,
    ResultMessage,
    CaliberationResultMessage,
    EmptyMessage,
}
