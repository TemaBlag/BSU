SELECT 
  r.rolname AS "Role",
  COALESCE(r.rolpassword, '') AS "Password",
  CASE 
    WHEN r.rolcanlogin THEN 'yes'
    ELSE 'no'
  END AS "Can login",
  CASE
    WHEN r.rolsuper THEN 'yes'
    ELSE 'no'
  END AS "Superuser",
  ARRAY_AGG(m.rolname) AS "Member of"
FROM 
  pg_roles r
  LEFT JOIN pg_auth_members am ON r.oid = am.roleid
  LEFT JOIN pg_roles m ON am.member = m.oid
GROUP BY 
  r.oid, r.rolname, r.rolpassword, r.rolcanlogin, r.rolsuper
ORDER BY 
  "Role";
  
CREATE USER "TempUser_N" WITH PASSWORD 'Password!';

ALTER USER "TempUser_N" WITH SUPERUSER;

SELECT rolname, rolsuper FROM pg_roles;

SELECT 
    r.rolname AS role_name,
    m.rolname AS member_name
FROM 
    pg_catalog.pg_auth_members am
    JOIN pg_catalog.pg_roles r ON r.oid = am.roleid
    JOIN pg_catalog.pg_roles m ON m.oid = am.member;
	
DROP USER "TempUser_N";
	
CREATE USER "TempUser_N" WITH PASSWORD 'MyFirstUser';	
	
GRANT SELECT ON ALL TABLES IN SCHEMA public TO "TempUser_N";	

CREATE USER "Andy" WITH PASSWORD 'password';

CREATE TABLE STUDENTS(
    skey_id BIGSERIAL PRIMARY KEY ,
    sfam INT ,
    stip INT,
    another_field INT
);
	
GRANT SELECT ON TABLE students TO "Andy";

GRANT SELECT ON TABLE students TO "Andy";

GRANT UPDATE (sfam, stip) ON TABLE students TO "Andy";

SELECT current_user;

SELECT * FROM information_schema.role_table_grants WHERE grantee = 'Andy';

REVOKE pg_monitor FROM "user";

DROP USER "user";