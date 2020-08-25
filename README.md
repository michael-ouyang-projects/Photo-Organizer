# Photo-Organizer

## Motivation
> **I had a great number of disorder photos camimg from different source like phone, computer, social media, cloud storage, etc.**

> **What i wanted to do is putting them all together and do the classification base on there creation time.**

---

## Usage
**1. First, put all your photos into the root directory regardless of there source.**
- you can put photos or just put directories that contain photos into the root directory.

---

**2. Second, run the application.**
- use the path of root directory that contain all your photos as the first argument.
- use the path of target directory as the second argument. (photos will copy from root directory to target directory)

---

**3. Check the result.**
- You can now find that all the photos are classified by there creation time and were put in the desired directory.
  - Ex: a.png creating at August, 2020 will be put in directory => 'target directory/2020/08/a.png'. 
  - Ex: b.png creating at December, 2019 will be put in directory => 'target directory/2019/12/b.png'.
