package converter;

import algorithm.EndruJarvis;
import algorithm.KeylKirkpatrik;
import algorithm.PointConnector;
import algorithm.Recursive;
import enums.AlgorithmChoice;

import java.util.Objects;

public class StringToAlgorithmConverter {

    public static PointConnector convert(String s){
        if(Objects.equals(s, AlgorithmChoice.KIRKPATRIK.getValue())){
            return new KeylKirkpatrik();
        }else if(Objects.equals(s, AlgorithmChoice.JARVIS.getValue())) {
            return new EndruJarvis();
        }else if(Objects.equals(s, AlgorithmChoice.RECURSIVE.getValue())){
            return new Recursive();
        }
        throw new UnsupportedOperationException("Other operations are not supported yet!");
    }
}
