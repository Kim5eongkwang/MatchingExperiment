package matchingQueue;

import student.Student;
import java.util.*;

public abstract class MatchingQueue {
    public List<Queue<Student>> applyQueues;    //학생 큐

    public int applicants = 0; //지원자

    public int capacity;   //수용인원

    public double maxCapacityRate = 1.0f;  //최대수용 인원 비율


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getApplicants() {
        return applicants;
    }

    public void setApplicants(int applicants) {
        this.applicants = applicants;
    }

    public double getMaxCapacityRate() {
        return maxCapacityRate;
    }

    public void setMaxCapacityRate(double maxCapacityRate) {
        this.maxCapacityRate = maxCapacityRate;
    }

    abstract public void resetQueues();

    /**
     * student 매칭 큐에 등록
     * @param student 등록할 학생
     * @return  정원이 모두 채워졌으면 false, 아니면 true 반환
     */
    abstract public boolean applyQueues(Student student);

    /**
     * 로직에 따라 매칭 큐의 특정 학생과 비교 후 스왑
     * @param student 비교할 학생
     * @return
     */
    abstract public Student swapStudent(Student student);

    /**
     * 최종 매칭된 결과 리스트 반환
     * @return
     */
    abstract public List<Student> match();

    abstract public Student popStudent(int prefer);
}
