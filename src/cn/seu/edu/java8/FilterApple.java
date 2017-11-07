package cn.seu.edu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
public class FilterApple {

    /**
     * 作为一个functionalInterface，其中有且只有一个public方法
     * default、static方法除外
     */
    @FunctionalInterface
    public interface AppFilter {
        boolean filter(Apple apple);
    }

    public static List<Apple> findApp(List<Apple> apples, AppFilter appFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static class GreenAnd150WeightFilter implements AppFilter {
        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("green") && apple.getWeight() >= 150;
        }
    }

    public static class YellowLess150WeightFilter implements AppFilter {
        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("yellow") && apple.getWeight() <= 150;
        }
    }

    public static List<Apple> findGreenApple (List<Apple> apples) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>(Arrays.asList(new Apple("green", 150), new Apple("yellow", 120), new Apple("green", 170)));
        List<Apple> greenApple = findGreenApple(list);
        assert greenApple.size() == 2 : "筛序错误";
        System.out.println(greenApple);
        System.out.println(".........................");

        List<Apple> result = findApp(list, new GreenAnd150WeightFilter());
        System.out.println(result);

        System.out.println("..................................");

        List<Apple> yellowApples = findApp(list, new AppFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });
        System.out.println(yellowApples);
        System.out.println(".......lamda.........");
        List<Apple> green = findApp(list, (Apple apple) -> {
            return  apple.getColor().equals("green");
        });
        System.out.println(green);

        System.out.println("........................");
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();

        System.out.println("...........lamda...........");
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
