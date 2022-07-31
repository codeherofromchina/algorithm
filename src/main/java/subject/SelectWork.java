package subject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther 王晓丹
 * @Date 2022/7/6 下午2:54
 */
public class SelectWork {

    public static void main(String[] args) {
        Job[] jobs = {new Job(1, 3), new Job(5, 20), new Job(1, 30), new Job(2, 10)};
        int[] persons = {1, 5, 3, 6, 2, 6, 3, 2};
        int[] choice = choice(jobs, persons);
        System.out.println(Arrays.toString(choice));

    }

    /**
     * 返回每个人选择的最大金额的工作
     *
     * @param jobArr
     * @param person
     * @return
     */
    public static int[] choice(Job[] jobArr, int[] person) {
        if (jobArr == null || jobArr.length == 0 || person == null) {
            return null;
        }
        Arrays.sort(jobArr, new JobComparator());

        TreeMap<Integer, Integer> jobMap = new TreeMap<>();
        jobMap.put(jobArr[0].hard, jobArr[0].money);
        int minMoney = jobArr[0].money;
        for (int i = 1; i < jobArr.length; i++) {
            int curMoney = jobArr[i].money;
            if (minMoney >= curMoney) {
                continue;
            }
            jobMap.put(jobArr[i].hard, curMoney);
            minMoney = curMoney;
        }

        int[] retArr = new int[person.length];
        for (int i = 0; i < person.length; i++) {
            Map.Entry<Integer, Integer> entry = jobMap.floorEntry(person[i]);
            retArr[i] = entry != null ? entry.getValue() : 0;
        }
        return retArr;
    }

    private static class Job {
        public Job(int hard, int money) {
            this.money = money;
            this.hard = hard;
        }

        private int money;
        private int hard;
    }


    private static class JobComparator implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            int hardCompare = Integer.compare(o1.hard, o2.hard);
            if (hardCompare == 0) {
                return -Integer.compare(o1.money, o2.money);
            }
            return hardCompare;
        }
    }
}
