# Database Changelog (Liquibase)

This folder contains the Liquibase changelog files used to manage and version-control the database schema for the project. Liquibase is an open-source database migration tool that allows you to define database changes (such as creating tables, inserting data, or altering columns) in a structured, trackable, and repeatable way. These changes are described in YAML files called "changelogs," which Liquibase applies in order to keep your database schema up to date.

## Folder Structure

```
db/
└── changelog/
    ├── changelog-master.yaml
    ├── README.md
    └── v1/
        ├── changelog-v1-master.yaml
        └── setting-table-changes.yaml
```

### File Descriptions

- **changelog-master.yaml**  
  The root changelog file. This file acts as the entry point for Liquibase and includes references to other changelog files. It currently includes the `v1/changelog-v1-master.yaml` file.

- **v1/changelog-v1-master.yaml**  
  The master changelog for version 1 of the database schema. This file includes all changesets related to version 1, such as table creation and initial data population. It includes the `setting-table-changes.yaml` file.

- **v1/setting-table-changes.yaml**  
  Contains the actual changesets for version 1, including:

  - Creation of the `setting` table with columns: `id`, `key`, and `value`.
  - Constraints such as primary key, uniqueness, and nullability.
  - Insertion of a default row for the `MAINTENANCE_STATUS` key with a value of `'false'`, using a precondition to avoid duplicate inserts.

- **README.md**  
  This file (you are reading it) explains the structure and purpose of the changelog folder.

## How Liquibase Works in This Project

1. **Changelog Hierarchy**  
   The changelog files are organized hierarchically. The `changelog-master.yaml` file is specified in the application configuration (`spring.liquibase.change-log`) and acts as the root. It includes other changelog files using the `include` directive, allowing for modular and versioned management of database changes.

2. **Versioning**  
   Each version of the schema has its own folder (e.g., `v1/`), making it easy to manage and track changes over time. Future versions can be added as new folders (e.g., `v2/`) with their own master changelog and changesets.

3. **Changesets**  
   Each changeset defines a single atomic change to the database, such as creating a table or inserting data. Changesets have unique IDs and authors for traceability.

4. **Preconditions**  
   Preconditions are used to ensure that changes are only applied when necessary. For example, the default `MAINTENANCE_STATUS` row is only inserted if it does not already exist.

## Usage

- Liquibase will automatically apply the changes defined in these changelog files when the application starts, based on the configuration in `application.properties`.
- To add new changes, create a new changeset YAML file and include it in the appropriate master changelog.
- To upgrade the schema, add a new version folder (e.g., `v2/`) and update `changelog-master.yaml` to include the new version's master changelog.

## References

- [Liquibase Documentation](https://www.liquibase.org/documentation/index.html)
- [Spring Boot + Liquibase Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.migration-tool.liquibase)

```<!-- filepath: d:\projects\lab\java\demo\src\main\resources\db\changelog\README.md -->

# Database Changelog (Liquibase)

This folder contains the Liquibase changelog files used to manage and version-control the database schema for the project. Liquibase is an open-source database migration tool that allows you to define database changes (such as creating tables, inserting data, or altering columns) in a structured, trackable, and repeatable way. These changes are described in YAML files called "changelogs," which Liquibase applies in order to keep your database schema up to date.

## Folder Structure

```

db/
└── changelog/
├── changelog-master.yaml
├── README.md
└── v1/
├── changelog-v1-master.yaml
└── setting-table-changes.yaml

```

### File Descriptions

- **changelog-master.yaml**
  The root changelog file. This file acts as the entry point for Liquibase and includes references to other changelog files. It currently includes the `v1/changelog-v1-master.yaml` file.

- **v1/changelog-v1-master.yaml**
  The master changelog for version 1 of the database schema. This file includes all changesets related to version 1, such as table creation and initial data population. It includes the `setting-table-changes.yaml` file.

- **v1/setting-table-changes.yaml**
  Contains the actual changesets for version 1, including:
  - Creation of the `setting` table with columns: `id`, `key`, and `value`.
  - Constraints such as primary key, uniqueness, and nullability.
  - Insertion of a default row for the `MAINTENANCE_STATUS` key with a value of `'false'`, using a precondition to avoid duplicate inserts.

- **README.md**
  This file (you are reading it) explains the structure and purpose of the changelog folder.

## How Liquibase Works in This Project

1. **Changelog Hierarchy**
   The changelog files are organized hierarchically. The `changelog-master.yaml` file is specified in the application configuration (`spring.liquibase.change-log`) and acts as the root. It includes other changelog files using the `include` directive, allowing for modular and versioned management of database changes.

2. **Versioning**
   Each version of the schema has its own folder (e.g., `v1/`), making it easy to manage and track changes over time. Future versions can be added as new folders (e.g., `v2/`) with their own master changelog and changesets.

3. **Changesets**
   Each changeset defines a single atomic change to the database, such as creating a table or inserting data. Changesets have unique IDs and authors for traceability.

4. **Preconditions**
   Preconditions are used to ensure that changes are only applied when necessary. For example, the default `MAINTENANCE_STATUS` row is only inserted if it does not already exist.

## Usage

- Liquibase will automatically apply the changes defined in these changelog files when the application starts, based on the configuration in `application.properties`.
- To add new changes, create a new changeset YAML file and include it in the appropriate master changelog.
- To upgrade the schema, add a new version folder (e.g., `v2/`) and update `changelog-master.yaml` to include the new version's master changelog.

## References

-
```
