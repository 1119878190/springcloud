package com.demo.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lx
 * @date 2021/3/8 18:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;
    private String serial;

}
