package cn.seu.edu.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Administrator on 2017/10/30.
 */
public class LamdaExpression {


        public static void main(String[] args) {
            Comparator<Apple> comparator = new Comparator<Apple>() {
                @Override
                public int compare(Apple o1, Apple o2) {
                    return o1.getColor().compareTo(o2.getColor());
                }
            };
            List<Apple> list = Collections.emptyList();
            list.sort(comparator);

            Comparator<Apple> comparator1 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
            Function<String, Integer> flamda = s -> s.length();

        }

}
