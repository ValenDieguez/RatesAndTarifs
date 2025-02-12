

CREATE TABLE pricesDTO (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      description VARCHAR(255),
                      dueDate TIMESTAMP
);
INSERT INTO pricesDTO (id, name, description, dueDate) VALUES
    (1, 'Task 1', 'This is Task 1', '2025-12-01T12:30:00.000Z');

INSERT INTO pricesDTO (id, name, description, dueDate) VALUES
    (2, 'Task 2', 'This is Task 2', '2025-12-01T12:30:00.000Z');

INSERT INTO pricesDTO (id, name, description, dueDate) VALUES
    (3, 'Task 3', 'This is Task 3', '2025-12-01T12:30:00.000Z');
