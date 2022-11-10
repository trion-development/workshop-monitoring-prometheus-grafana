# Beispielprojekt für Monitoring mit Prometheus und Grafana

## Software

git

Für die Java Beispiel Java JDK in Version 17.
Docker und docker-compose empfohlen.
Alternativ können die Binaries direkt heruntergeladen und genutzt werden.

Prometheus Download: https://prometheus.io/download/

AlertManager Download: https://prometheus.io/download/

Grafana Download: https://grafana.com/grafana/download


## Einrichtung

Linux:
`mkdir data & mkdir data/prometheus & mkdir data/grafana`
`chmod -R a+w data`

## Beispiele
In den Ordnern `spring` und `prometheus` befinden sich Beispielprojekte mit unterschiedlichen Zwischenständen zur leichten Nachvollziehbarkeit.

### Beispiel-Aufrufe

```
$ watch -n 5 curl -sSL http://localhost:8080/account/register
$ watch -n 3 curl -sSL http://localhost:8080/login
$ watch -n 2 curl -sSL http://localhost:8080/shop
$ watch -n 2 curl -sSL http://localhost:8080/search
```
