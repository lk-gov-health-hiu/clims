/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data;

/**
 *
 * @author Buddhika
 */
public enum DepartmentListMethod {
    LoggedDepartmentOnly,
    AllDepartmentsOfLoggedInstitution,
    AllDepartmentsOfAllInstitutions,
    AllPharmaciesOfLoggedInstitution,
    AllPharmaciesOfAllInstitutions,
    ActiveDepartmentsOfLoggedInstitution,
    ActiveDepartmentsOfAllInstitutions,
}
