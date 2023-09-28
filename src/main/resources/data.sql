-- Create initial Devices
INSERT INTO device(uuid, model, phone_number,serial_number) VALUES (RANDOM_UUID(), 'BASE', '+49111222', 'RFAEFASDSA');
INSERT INTO device(uuid, model, phone_number,serial_number) VALUES (RANDOM_UUID(), 'BASE', '+49111333', 'RFAEFASDSF');
INSERT INTO device(uuid, model, phone_number,serial_number) VALUES (RANDOM_UUID(), 'BASE', '+49111444', 'RFAEFASDSD');
INSERT INTO device(uuid, model, phone_number,serial_number) VALUES (RANDOM_UUID(), 'BASE', '+49111555', 'RFAEFASDSE');

-- Create initial Holders
INSERT INTO holder(uuid, birthday, address,first_name, last_name) VALUES (RANDOM_UUID(), '1930-01-18', 'Example Adress 1', 'Test', 'First');
INSERT INTO holder(uuid, birthday, address,first_name, last_name) VALUES (RANDOM_UUID(), '1935-01-18', 'Example Adress 2', 'Test', 'Second');
INSERT INTO holder(uuid, birthday, address,first_name, last_name) VALUES (RANDOM_UUID(), '1940-01-18', 'Example Adress 3', 'Test', 'Third');
INSERT INTO holder(uuid, birthday, address,first_name, last_name) VALUES (RANDOM_UUID(), '1950-01-18', 'Example Adress 4', 'Test', 'Forth');
