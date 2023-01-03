SELECT
  person_id,
  concat(name, '(', substr(profession, 1, 1), ')') AS name
FROM
  Person
ORDER BY
  person_id DESC