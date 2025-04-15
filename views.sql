create or replace view products_per_category as
select c.id        as category_id,
       count(p.id) as num_products
from category c
         left join
     product p on p.category_id = c.id
group by c.id;

create
materialized view products_per_manufacturers as
select m.id        as manufacturer_id,
       count(p.id) as num_products
from manufacturers m
         left join
     product p on p.manufacturer_id = m.id
group by m.id;
