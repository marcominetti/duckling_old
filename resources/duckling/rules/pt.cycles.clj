; See comments in en.cycles.clj

(
  "segundo (cycle)"
  #"(?i)segundos?"
  {:dim :cycle
   :grain :second}

  "minutos (cycle)"
  #"(?i)minutos?"
  {:dim :cycle
   :grain :minute}

  "hora (cycle)"
  #"(?i)horas?"
  {:dim :cycle
   :grain :hour}

  "dia (cycle)"
  #"(?i)d(í|i)as?"
  {:dim :cycle
   :grain :day}

  "semana (cycle)"
  #"(?i)semanas?"
  {:dim :cycle
   :grain :week}

  "mes (cycle)"
  #"(?i)m[eê]s(es)?"
  {:dim :cycle
   :grain :month}
  
  "ano (cycle)"
  #"(?i)anos?"
  {:dim :cycle
   :grain :year}
  
  "este <cycle>"
  [#"(?i)(n?es[st](es?|as?))" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "o <cycle> passado"
  [#"(?i)os?|as?" (dim :cycle) #"(?i)passad(a|o)s?"]
  (cycle-nth (:grain %2) -1)

  "no <cycle> (que vem)"
  [#"(?i)n?as?|n?os?" (dim :cycle) #"(?i)que vem|seguintes?"]
  (cycle-nth  (:grain %2) 1)
  
  "no proximo <cycle> "
  [#"(?i)n?as?|n?os?" #"(?i)pr(ó|o)xim(o|a)s?" (dim :cycle)]
  (cycle-nth  (:grain %3) 1)

  "o <cycle> antes de <time>"
  [#"(?i) n?as?|n?os?" (dim :cycle) #"(?i)antes d[eo]" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)

  "passados n <cycle>"
  [#"(?i)passad(a|o)s?" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "n <cycle> atras"
  [(integer 2 9999) (dim :cycle) #"(?i)atr[aá]s"]
  (cycle-n-not-immediate (:grain %2) (- (:value %1)))

  "n passados <cycle>"
  [(integer 2 9999) #"(?i)passad(a|o)s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %1)))
  
  "proximas n <cycle>"
  [#"(?i)pr(ó|o)xim(o|a)s?" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "n proximas <cycle>"
  [(integer 2 9999) #"(?i)pr(ó|o)xim(o|a)s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %1))

  "n <cycle> (proximo|que vem)"
  [(integer 2 9999) (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vem?|seguintes?)"]
  (cycle-n-not-immediate  (:grain %2) (:value %1))
)
