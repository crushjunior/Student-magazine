CREATE TABLE tasks(
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR (25) NOT NULL,
  project_id INTEGER NOT NULL,
FOREIGN KEY (project_id) REFERENCES projects(id)
);