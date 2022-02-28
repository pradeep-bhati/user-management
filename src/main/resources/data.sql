INSERT INTO user (user_name, account_non_expired, account_non_locked, credentials_non_expired,enabled,first_name,last_name,mail_id,password)
VALUES ('root', true, true, true, true, 'pradeep', 'bhati', 'er.pradeep47@gmail.com','$2a$10$QJOu0/F6bGjdvnRVDPCfpuHdI41oSUxoVBVVRsEGsn.ZHZwsAZAie');
INSERT INTO role (id,roles,user_name) VALUES (1,'ROLE_USER','root');
INSERT INTO role (id,roles,user_name) VALUES (2,'ROLE_ADMIN','root');
UPDATE hibernate_sequence set next_val = 3 where next_val = 1;