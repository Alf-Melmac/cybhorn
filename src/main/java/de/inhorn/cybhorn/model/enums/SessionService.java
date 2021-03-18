package de.inhorn.cybhorn.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

    
@Getter
@AllArgsConstructor
public enum SessionService {
    Call(0), 
    Browsing(2), 
    Download(10), 
    Video(100); 

    private final double required;
}