select
  sample_id,
  dna_sequence,
  species,
  dna_sequence like 'ATG%' as has_start,
  dna_sequence like '%TAA'
  or dna_sequence like '%TAG'
  or dna_sequence like '%TGA' as has_stop,
  dna_sequence like '%ATAT%' as has_atat,
  dna_sequence like '%GGG%' as has_ggg
from
  Samples
order by
  sample_id