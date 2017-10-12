prix_base(rt,12,essence,10000).
prix_base(rte,14,essence,11500).
prix_base(pampa,14,essence,12000).
prix_base(rd,19,diesel,11500).
prix_option(rt,airbag,500).
prix_option(rt,abs,750).
prix_option(rte,peinture_metallise,750).
prix_option(rte,airbag,500).
prix_option(rte,abs,750).
prix_option(rte,climatisation,600).
prix_option(pampa,peinture_metallise,750).
prix_option(pampa,airbag,500).
prix_option(pampa,abs,750).
prix_option(pampa,climatisation,600).
prix_option(rd,peinture_metallise,750).
prix_option(rd,peinture_metallise,750).
prix_option(rd,climatisation,600).

cout_airbag(_,0,PrixAirbag):-PrixAirbag is 0.
cout_airbag(Modele,1,PrixAirbag):-prix_option(Modele,airbag,PrixAirbag).

cout_abs(_,0,PrixAbs):-PrixAbs is 0.
cout_abs(Modele,1,PrixAbs):-prix_option(Modele,abs,PrixAbs).


cout_clim(_,0,PrixClim):-PrixClim is 0.
cout_clim(Modele,1,PrixClim):-prix_option(Modele,climatisation,PrixClim).


cout_peint(_,0,PrixMetal):-PrixMetal is 0.
cout_peint(Modele,1,PrixMetal):-prix_option(Modele,peinture_metallise,PrixMetal).


cout_options(Prix_Options, Modele, Metal, Airbag, ABS, Clim):-
cout_peint(Modele,Metal,PrixMetal),
cout_airbag(Modele,Airbag,PrixAirbag),
cout_abs(Modele,ABS,PrixAbs),
cout_clim(Modele,Clim,PrixClim),
Prix_Options is PrixMetal+PrixAirbag+PrixAbs+PrixClim.

cout_total(Prix_Total, Modele, Metal, Airbag, ABS, Clim):-prix_base(Modele,_,_,Prixvoiture),
cout_options(Prix_Options, Modele, Metal, Airbag, ABS, Clim),Prix_Total is Prix_Options+Prixvoiture.
cout(Modele,Prix):-prix_base(Modele,_,_,Prix).

choix_budget(Prix_Max, Modele, Metal, Airbag, ABS, Clim):-cout_total(Prix_Total, Modele, Metal, Airbag, ABS, Clim),Prix_Total=<Prix_Max.