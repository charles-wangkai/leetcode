# https://leetcode.com/problems/analyze-organization-hierarchy/solutions/6521992/sql-solutions-recursive-query-to-link-subordinates-to-an-employee-then-aggregate/
with recursive org_hierarchy (
  orig_employee_id,
  orig_employee_name,
  employee_id,
  employee_name,
  manager_id,
  salary,
  org_level
) as (
  select
    employee_id as orig_employee_id,
    employee_name as orig_employee_name,
    employee_id,
    employee_name,
    manager_id,
    salary,
    1 as org_level
  from
    Employees
  UNION
  ALL
  select
    P.orig_employee_id,
    P.orig_employee_name,
    CH.employee_id,
    CH.employee_name,
    CH.manager_id,
    CH.salary,
    P.org_level + 1
  from
    org_hierarchy P,
    Employees CH
  where
    ch.manager_id = P.employee_id
),
CEO_hierarchy as (
  select
    org_hierarchy.employee_id as SUB_employee_id,
    org_hierarchy.employee_name,
    org_hierarchy.org_level as sub_level
  from
    org_hierarchy,
    Employees
  where
    org_hierarchy.orig_employee_id = Employees.employee_id
    and Employees.manager_id is null
)
select
  org_hierarchy.ORIG_EMPLOYEE_ID as employee_id,
  org_hierarchy.ORIG_EMPLOYEE_name as employee_name,
  CEO_hierarchy.sub_level as "level",
  count(*) - 1 as team_size,
  sum(org_hierarchy.salary) as budget
from
  org_hierarchy,
  CEO_hierarchy
where
  org_hierarchy.ORIG_EMPLOYEE_ID = CEO_hierarchy.SUB_employee_id
group by
  org_hierarchy.ORIG_EMPLOYEE_ID,
  org_hierarchy.ORIG_EMPLOYEE_name,
  CEO_hierarchy.sub_level
order by
  3 asc,
  5 desc,
  2