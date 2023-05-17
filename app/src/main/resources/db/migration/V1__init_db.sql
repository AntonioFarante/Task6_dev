CREATE TABLE IF NOT EXISTS worker (
	id IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	birthday DATE,
	`level` VARCHAR(100) NOT NULL,
	salary INTEGER
);
ALTER TABLE worker
ADD CONSTRAINT level_enum_values
CHECK (`level` IN ('Trainee','Junior','Middle','Senior'));

CREATE TABLE IF NOT EXISTS client (
	id IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS project(
	id IDENTITY PRIMARY KEY,
	client_id BIGINT,
	start_date DATE,
	finish_date DATE
);

CREATE TABLE IF NOT EXISTS project_worker (
	project_id BIGINT,
	worker_id BIGINT,
	FOREIGN KEY (project_id)
	REFERENCES project(id),
	FOREIGN KEY (worker_id)
	REFERENCES worker(id)

);