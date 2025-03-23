package com.practice.domain;


/*
 * @Created 3/22/25
 * @Project springboot-microservice-kafka
 * @User Kumar Padigeri
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
private int employeeID;
private String name;
private int age;
}
