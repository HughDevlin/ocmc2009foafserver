CREATE OR REPLACE VIEW FoafInterest AS
SELECT node_id, label AS interest
FROM node_attributes JOIN question_fields
ON SUBSTRING(node_attributes.attr_key FROM 9)=question_fields.name
WHERE SUBSTRING(node_attributes.attr_key FROM 1 FOR 8) = "F`FIELD`"
