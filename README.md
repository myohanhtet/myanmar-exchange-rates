## myanmar exchange rates
**Banks**
- Yoma
- Aya
- Kbz

**Type**
- usd
- sgd
- eur
- thb
- myr

**Request :**
`Get: http://localhost:8181/{bank}`

**Response :**
```json
    {
        "bank": "Yoma",
        "date": "2020-06-30",
        "buy": {
            "myr": "305",
            "sgd": "975",
            "eur": "1490",
            "usd": "1370",
            "thb": "40"
        },
        "sell": {
            "myr": "325",
            "sgd": "995",
            "eur": "1525",
            "usd": "1375",
            "thb": "45"
        }
    }
```

### Get Single Rate

**Request:**
`Get: http://localhost:8181/{bank}/{type}`

**Response**
```json
  {
    "usd": {
        "buy": "1358",
        "sell": "1365"
    }
}
```