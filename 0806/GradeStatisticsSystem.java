public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = grades[0], min = grades[0];
        int[] gradeCount = new int[5]; // A, B, C, D, F
        for (int grade : grades) {
            sum += grade;
            if (grade > max) max = grade;
            if (grade < min) min = grade;

            if (grade >= 90) gradeCount[0]++;
            else if (grade >= 80) gradeCount[1]++;
            else if (grade >= 70) gradeCount[2]++;
            else if (grade >= 60) gradeCount[3]++;
            else gradeCount[4]++;
        }

        double avg = sum / (double) grades.length;
        int aboveAvg = 0;
        for (int g : grades) {
            if (g > avg) aboveAvg++;
        }

        System.out.println("平均成績: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("等第人數: A=" + gradeCount[0] + ", B=" + gradeCount[1] + ", C=" + gradeCount[2] + ", D=" + gradeCount[3] + ", F=" + gradeCount[4]);
        System.out.println("高於平均的人數: " + aboveAvg);
        System.out.print("所有成績: ");
        for (int g : grades) System.out.print(g + " ");
    }
}