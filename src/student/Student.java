package student;

import java.util.LinkedList;
import java.util.List;

public class Student implements Comparable<Student>{
    public static final int MAX_APPLY = 3;

    private String studentID; // 학번

    private List<String> preferred; // 선호 학과 ID 목록 : 학과 별로 선호도가 다르다. (0, 1, 2, ... 지망)
    private List<String> nonPreferred; // 비 선호 학과 ID 목록

    private String matchedDepartment;   //최종 매칭된 학과

    private double grade = 0; // 성적

    private int matchingCounter = 0;    //다음으로 매칭될 학생의 선호도

    public Student(String id) {
        preferred = new LinkedList<>();
        nonPreferred = new LinkedList<>();
        studentID = id;
        grade = 0;
        matchingCounter = 0;
        matchedDepartment = null;
    }

    public int getMatchingCounter() {
        return matchingCounter;
    }

    public int upMatchingCounter() {
        return matchingCounter++;
    }

    public int getApplyCount() {
        return preferred.size();
    }

    public void resetMatching() {
        matchingCounter = 0;
        matchedDepartment = null;
    }

    public String getPreferedDepartmentID(int prefer) {
        return preferred.get(prefer);
    }

    public int getPreference() {
        for(int prefer = 0; prefer < getApplyCount(); prefer++) {
            if(preferred.get(prefer).equals(matchedDepartment)) {
                return Student.MAX_APPLY - prefer;
            }
        }
        return 0;
    }

    public int fetchPrefer() {
        return matchingCounter < getApplyCount() ? matchingCounter++ : matchingCounter;
    }

    @Override
    public int compareTo(Student o) { // 학점이 높은 순으로 비교
        if(o.grade > this.grade)
            return 1;
        else if(o.grade == this.grade)
            return 0;
        return -1;
    }

    public void setMatchedDepartment(String matchedDepartment) {
        this.matchedDepartment = matchedDepartment;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * 선호 학과를 등록한다.
     * 학생의 선호도가 높은 학과부터 등록해야한다.
     * @param departmentId 선호 학과 ID
     * @return 최대 지원 횟수를 넘겼거나, 이미 등록된 학과라면 false를 반환한다.
     */
    public boolean addPreferred(String departmentId) {
        if(preferred.size() >= MAX_APPLY) {
            return false;
        }
        for(String prefer : preferred) {
            if(prefer.equals(departmentId)) {
                return false;
            }
        }
        preferred.add(departmentId);
        return true;
    }

    public double getGrade() {
        return grade;
    }
}
