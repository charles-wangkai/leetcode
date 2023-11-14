SELECT
  abs(
    (
      SELECT
        max(salary)
      FROM
        Salaries
      WHERE
        department = 'Engineering'
    ) -(
      SELECT
        max(salary)
      FROM
        Salaries
      WHERE
        department = 'Marketing'
    )
  ) AS salary_difference