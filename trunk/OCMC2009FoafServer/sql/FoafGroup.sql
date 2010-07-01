CREATE OR REPLACE VIEW FoafGroup AS
SELECT DISTINCT organization, REPLACE(SUBSTRING_INDEX(organization, ' / ', -1), '&', ' and ') AS organization_display_name
  FROM OCMC2009.FoafPerson