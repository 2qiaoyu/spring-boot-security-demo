package com.joham.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author joham
 */
@Data
@AllArgsConstructor
public class Blog {

    private Long id;

    private String name;

    private String content;
}
