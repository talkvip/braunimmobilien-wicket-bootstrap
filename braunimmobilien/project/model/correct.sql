UPDATE Strassen str left join Orte ort on str.OrtID=ort.OrtID SET str.OrtID=1476 WHERE  ort.OrtID is null;
UPDATE Links ln left join Subjects sb on ln.subjectID=sb.subjectID SET ln.subjectID=10 WHERE   sb.subjectID is null;
UPDATE  Angebote ang left join Konditionen kon on ang.Konditionen=kon.KonID SET ang.Konditionen="kon" WHERE   kon.KonID is null;
UPDATE  Objekte obj left join Strassen str on obj.ObjStrasseID=str.StrID SET obj.ObjStrasseID=15569 WHERE   str.StrID is null;
UPDATE  Objekte obj left join Objektsuch su on obj.ObKeinKontakt=su.ObjektsuchID SET obj.ObKeinKontakt=1 WHERE   su.ObjektsuchID is null;
UPDATE  Objekte obj SET obj.ObjArtID=1 WHERE   obj.ObjArtID is null;
UPDATE  Eigentuemer eig left join Strassen str on eig.EigtStrID=str.StrID SET eig.EigtStrID=15569 WHERE   str.StrID is null;
UPDATE  Eigentuemer eig  SET eig.EigtInfo="" WHERE   eig.EigtID=17678;
UPDATE  Eigentuemer eig  SET eig.EigtInfo="" WHERE   eig.EigtID=18543;


UPDATE  Kunden kund left join Eigentuemer eig on kund.KundEigtNr=eig.EigtID SET kund.KundEigtNr=6958 WHERE   eig.EigtID is null;
INSERT INTO `Kunden` VALUES (2,6,16,1,'1998-01-13');
UPDATE  Nachweise nach left join Eigentuemer eig on nach.NachEigtNr=eig.EigtID SET nach.NachEigtNr=6958 WHERE   not(nach.NachEigtNr is null) and eig.EigtID is null;
UPDATE  Nachweise nach left join Mitrumpf eig on nach.NachMitarbNr=eig.Mitnummer SET nach.NachMitarbNr=2 WHERE   not(nach.NachMitarbNr is null) and eig.Mitnummer is null;
UPDATE  Nachweise nach left join Angebote ang on nach.NachAngNr=ang.AngNr SET nach.NachAngNr="RH100" WHERE   not(nach.NachAngNr is null) and ang.AngNr is null;
UPDATE  Nachweise nach left join Angebote ang on nach.NachAngNr1=ang.AngNr SET nach.NachAngNr1="RH100" WHERE   not(nach.NachAngNr1 is null) and ang.AngNr is null;
INSERT INTO `XTyp` VALUES (110,'XDummy','XBrief',1);
UPDATE  Nachweise nach left join XTyp xtyp on nach.NachXNr=xtyp.XTypNr SET nach.NachXNr=110 WHERE   not(nach.NachXNr is null) and xtyp.XTypNr is null;
UPDATE  Nachweise nach left join Kunden kund on nach.NachKundNr=kund.KundenNr SET nach.NachKundNr=2 WHERE   not(nach.NachKundNr is null) and kund.KundenNr is null;
UPDATE  Nachweise nach SET nach.NachEigtNr=null WHERE   nach.NachEigtNr=0;
UPDATE  Nachweise nach SET nach.NachObjNr=null WHERE   nach.NachObjNr=0;
UPDATE  Scout scout left join Orte ort on scout.OrtID=ort.OrtID SET scout.OrtID=1 WHERE   not(scout.OrtID is null) and ort.OrtID is null;
UPDATE  Scout scout left join Objekte obj on scout.ObjID=obj.ObjID SET scout.ObjID=null WHERE   not(scout.ObjID is null) and obj.ObjID is null;
UPDATE  Scout scout left join Eigentuemer eig on scout.EigtID=eig.EigtID SET scout.EigtID=null WHERE   not(scout.EigtID is null) and eig.EigtID is null;

Delete from Eigentuemermuster where Zuordnungsnummer=386;
Delete from Eigentuemermuster where Zuordnungsnummer=570;
Delete from Eigentuemermuster where Zuordnungsnummer=406;
Delete from Eigentuemermuster where Zuordnungsnummer=440;
Delete from Eigentuemermuster where Zuordnungsnummer=520;
Delete from Eigentuemermuster where Zuordnungsnummer=549;
/*Strasse ohne Ort
/*15816
/*select `StrID` from 
/*  Strassen str left join Orte ort on str.OrtID=ort.OrtID
/*where ort.OrtID is null
/*1,5,6
/*select `linksID` from 
/*  Links ln left join Subjects sb on ln.subjectID=sb.subjectID
/*where sb.subjectID is null
/*HS0
/*select `AngNr` from 
/*  Angebote ang left join Konditionen kon on ang.Konditionen=kon.KonID
/*where kon.KonID is null
/*145,146,1783-1788,10201-10204,17675
/*select `ObjID` from 
/*  Objekte obj left join Strassen str on obj.ObjStrasseID=str.StrID
/*where str.StrID is null
/*14347,17675
/*select `ObjID` from 
/*  Objekte obj left join Objektsuch su on obj.ObKeinKontakt=su.ObjektsuchID
/*where su.ObjektsuchID is null
/*Eigentuemer 15846,16669 kein UTF-8
/*55,325,326,785,28192,11356,21917,22138,22520,28199,28200,28206,28207,28209,28213,28214,28247,28248
/*select `EigtID` from 
/*  Eigentuemer eig left join Strassen str on eig.EigtStrID=str.StrID
/*where str.StrID is null
/*select `KundenNr` from 
/*  Kunden kund left join Eigentuemer eig on kund.KundEigtNrD=eig.EigtID
/*where eig.EigtID is null

/*select `NachweisNr` from 
/*  Nachweise nach left join Eigentuemer eig on nach.KundEigtNrD=eig.EigtID
/*where eig.EigtID is null

/*select `NachweisNr` from 
/*  Nachweise nach left join Mitrumpf eig on nach.NachMitarbNr=eig.Mitnummer
/*where eig.Mitnummer is null

