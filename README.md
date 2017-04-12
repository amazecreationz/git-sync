# GitSync - Under Development

JAVA Application to commit and push changes to git automatically.



## .gitsync - The GitSync Configuration File

It contains all configuration information in JSON. It has information about git credentials and project details.
The file is automatically created in the application root.

Sample JSON:
```
{
   "USERNAME": "anandmoghan",
   "PASSWORD": "password",
   "PROJECTS": [
      {
         "PROJECT_NAME": "amazecreationz",
         "PROJECT_SOURCE": "/projects/amazecreationz",
         "PROJECT_DESTINATION": "/repo/amazecreationz"
      },
      {
         "PROJECT_NAME": "reachalbert",
         "PROJECT_SOURCE": "/projects/reachalbert",
         "PROJECT_DESTINATION": "/repo/reachalbert"
      }
   ]
}
```

For any clarifications, mail us at <anandmoghan@amazecreationz.in>
