CREATE OR REPLACE VIEW FoafPerson AS
SELECT node_id, username, REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(phone, ' ', ''), ')', ''), '(', ''),
  '.', ''), '-', '') AS phone, label, first_name, last_name, email, uri, organization
  FROM OCMC2009.nodes 
  WHERE node_id <> 1 AND type = 'user' AND last_name <> 'yao' AND username <> 'Guest'






