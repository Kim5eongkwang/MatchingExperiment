package matchingQueue;

import student.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class StandardQueue extends MatchingQueue {

    public StandardQueue(int capacity) {
        this.capacity = capacity;
        this.applicants = 0;
        this.maxCapacityRate = 1.0f;
    }

    @Override
    public void resetQueues() {
        for(int i = 0; i < Student.MAX_APPLY + 1; i++) { // [n지망 지원 큐] + [n지망 모두 떨어진 학생 지원 큐]
            applyQueues.add(new PriorityQueue<>()); //우선 순위 큐 사용
        }
    }
    @Override
    public boolean applyQueues(Student student) {
        int preferNumber = student.getMatchingCounter() < applicants ? student.upMatchingCounter() : student.getMatchingCounter();

        if(applicants >= capacity * maxCapacityRate) {
            return false;
        }
        applyQueues.get(preferNumber).add(student);
        applicants++;
        return true;

    }

    @Override
    public boolean swapStudent(Student student) {
        return false;
    }

    @Override
    public List<Student> match() {
        return List.of();
    }

    @Override
    public Student popStudent(int prefer) {
        return null;
    }
}
