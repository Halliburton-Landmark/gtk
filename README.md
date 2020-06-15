How to build
================================================
First see [related section](#prepare-build-environment) to configure build environment.

1. Clone https://gitlab.gnome.org/GNOME/gtk/-/tree/3.22.30
2. Apply all patches located in SOURCES dir
3. Put sources into a folder called gtk+-%{version} and package it as gtk+-%{version}.tar.xz (remove .git folder to speed the process)
4. Put the archive under SOURCES directory
5. Build RPM by running:
   `rpmbuild -bb gtk3.spec`
6. Execute tests from manual-tests folder to test the patches.


Prepare build environment
================================================
Steps below assume that build will be run on CentOS 7.4 without packages installed from later releases.
This ensures that produced binaries will be compatible with CentOS 7.4+.

1. Configure yum repositories

Add the following config to /etc/yum.repos.d/CentOS-Vault.repo


```
[C7.4.1708-base]
name=CentOS-7.4.1708 - Base
baseurl=http://vault.centos.org/7.4.1708/os/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
enabled=1


[C7.4.1708-CR]
name=CentOS-7.4.1708 - CR
baseurl=http://vault.centos.org/7.4.1708/cr/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
enabled=1
```

Make sure other repositories are disabled.

2. Install required packages
`yum install gtk-doc gobject-introspection-devel glib2-devel atk-devel cairo-devel cairo-gobject-devel  pango-devel gdk-pixbuf2-devel libXinerama-devel libXi-devel libXrandr-devel libXcomposite-devel libepoxy-devel at-spi2-atk-devel colord-devel libXcursor-devel cups-devel rest-devel json-glib-devel avahi-gobject-devel libxkbcommon-devel  libwayland-client-devel libwayland-cursor-devel mesa-libwayland-egl-devel wayland-protocols-devel`


See also https://developer.gnome.org/gtk3/stable/gtk-building.html