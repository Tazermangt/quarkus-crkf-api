package fr.ot.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair<U, V> {
    private U first;
    private V second;
}
