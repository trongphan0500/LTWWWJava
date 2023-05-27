package vn.edu.iuh.fit.resouces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.models.Student;

@RestController
@RequestMapping("/api")
public class ProductResources {
    @GetMapping("/test")
    public ResponseEntity<Student> getTest() {
        ResponseEntity<Student> s = ResponseEntity.ok(new Student(100l, "Nguyen van teo"));
        return s;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<Student> testAdmin() {
        ResponseEntity<Student> s = ResponseEntity.ok(new Student(101l, "Administrator"));
        return s;
    }
}
