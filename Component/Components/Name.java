package com.jjdx.ecosystem.Component.Components;

import com.jjdx.ecosystem.Component.Component;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Name extends Component {
    String name;

    @Override
    public String toString() {
        return "name(" + name + ")";
    }

}
