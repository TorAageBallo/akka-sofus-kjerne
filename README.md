akka-sofus-kjerne
=================
POC på Akka-system for å håndtere skatt-/forskuddsberegning av et stort antall skattytere. 

Systemet tar i mot en liste med identifikatorer til skattyterne. Listen deles opp og distribueres til ulike Actors for henting av grunnlag med bruk av RoundRobin-router. GrunnlagsActorne forespørr alle beregningsgrunnlagene i paralell. Når alle grunnlagene som behøves er samlet returneres disse til MasterActoren som distribuerer beregningsgrunnlagene videre til skatteberegning ved bruk av en RoundRobin-router.

Applikasjone kan kjøres fra Main.class, som kaller service som tar i mot listen av identifikatorer.
