CREATE TABLE student_tasks (
                             id BIGSERIAL PRIMARY KEY,
                             student_id INTEGER NOT NULL,
                             task_id INTEGER NOT NULL,
                             FOREIGN KEY (student_id) REFERENCES students(id),
                             FOREIGN KEY (task_id) REFERENCES tasks(id)
);