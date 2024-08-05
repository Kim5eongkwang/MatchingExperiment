package matchingQueue;

import student.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
    public Student swapStudent(Student student, int preferNumber) {
        Queue<Student> applyQueue = applyQueues.get(preferNumber);
        // 1. 낮은 지망으로 지원한 학생과 교체
        for(int i = Student.MAX_APPLY; i > preferNumber; i--) {
            Student swappedStudent = popStudent(i);
            if(swappedStudent != null) {
                applyQueue.add(student);
                applicants++;
                return swappedStudent;
            }
        }
        // 2. 낮은 지망이 없다면, 같은 지망으로 지원한 학생 중 성적 비교 후 교체
        if(applyQueue.isEmpty()) {
            return student;
        }
        Student swap = applyQueue.peek();
        if(swap.getGrade() < student.getGrade()) { // 성적이 같은 경우는 어떻게 해야할까?
            applyQueue.add(student);
            return applyQueue.poll();
        }
        // 3. 성적도 낮으면, 교체되지 않고 반환
        return student;
    }


    @Override
    public Student popStudent(int prefer) {
            Queue<Student> preferQueue = applyQueues.get(prefer);
            if(preferQueue.isEmpty())
                return null;
            applicants--;
            return preferQueue.poll();
    }
}
