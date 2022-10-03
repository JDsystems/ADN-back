select  COUNT(i.*) as total
from invitacion i
INNER JOIN membresia me ON me.id=i.id_membresia
where me.id_cliente = :id_cliente