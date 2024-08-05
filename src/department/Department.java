package department;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import matchingQueue.MatchingQueue;
import matchingQueue.StandardQueue;
import student.Student;

public class Department {

    private String id;  //학과 아이디

    private MatchingQueue applyQueues;

    public Department(String id, int capacity) {
        this.id = id;

        applyQueues = new StandardQueue(capacity);
        /*
        applyQueues = new LinkedList<>();
        for(int i = 0; i < Student.MAX_APPLY + 1; i++) { // [n지망 지원 큐] + [n지망 모두 떨어진 학생 지원 큐]
            applyQueues.add(new PriorityQueue<>()); //우선 순위 큐 사용    //수정할 부분
        }
         */
    }

    public void resetMatching(){
        applyQueues.resetQueues();
    }

    public boolean apply(Student student) {
        return applyQueues.applyQueues(student);
    }

    public Student swap(Student student, int preferNumber){
       return applyQueues.swapStudent(student, preferNumber);
    }

    /**
     * ㅣ해당 학과에 지원한 학생을 "확정"하고 리스트로 만든다.
     * @return 매칭된 학생 리스트
     */
    public List<Student> match(){
        List<Student> matching = new LinkedList<>();
        for(Queue<Student> applyQueue : applyQueues.getApplyQueues()) {
            while(!applyQueue.isEmpty()) {
                Student student = applyQueue.poll();
                student.setMatchedDepartment(id);
                matching.add(student);
            }
        }
        return matching;
    }

    /**
     * [prefer] 순위로 지원한 학생 중, 가장 낮은 학점을 가진 학생을 반환한다.
     * @param prefer 지원 순위
     * @return 반환된 학생
     */
    public Student popStudent(int prefer){
        return applyQueues.popStudent(prefer);
    }

    public boolean isFull(){
        return getApplicants() >= applyQueues.getCapacity() * applyQueues.getMaxCapacityRate();
    }

    public void setMaxCapacityRate(double maxCapacityRate) {
        applyQueues.setMaxCapacityRate(maxCapacityRate);
    }

    public void setCapacity(int capacity) {
        applyQueues.setCapacity(capacity);
    }

    public int getCapacity(){
        return applyQueues.getCapacity();
    }

    public String getId(){
        return id;
    }

    public int getApplicants(){
        return applyQueues.getApplicants();
    }


}
