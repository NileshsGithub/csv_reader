    package com.example.csv_reader.controller;

    import com.example.csv_reader.service.PersonService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;

    @RestController
    @RequestMapping("/api/person")
    public class PersonController {

        private final PersonService personService;

        public PersonController(PersonService service) {
            this.personService = service;
        }

        @PostMapping(value = "/upload", consumes = {"Multipart/form-data"})
        public ResponseEntity<Integer> upload(
                @RequestPart("file")MultipartFile file) throws IOException {
                return ResponseEntity.ok(personService.uploadPerson(file));
        }


        @GetMapping("/load")
        public ResponseEntity<String> loadFromClasspath() {
            try {
                int count = personService.loadFromClasspath("csv/persons.csv");
                return ResponseEntity.ok("Loaded " + count + " persons");
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error: " + e.getMessage());
            }
        }
    }
