How to build
================================================
First see [related section](#prepare-build-environment) to configure build environment.

1. Clone gtk repository
```
git clone https://gitlab.gnome.org/GNOME/gtk.git -b 3.22.30 --single-branch gtk+-3.22.30
```
2. Put sources into a folder called gtk+-%{version} and package it as gtk+-%{version}.tar.xz (remove .git folder to speed the process)
```
rm -rf gtk+-3.22.30/.git
tar cfJ gtk+-3.22.30.xz gtk+-3.22.30
```
3. Put the archive under SOURCES directory
4. Put content of this repository under $HOME/rpmbuild
4. Build RPM by running:
```
cd $HOME/rpmbuild/SPECS
rpmbuild -bb gtk3.spec
```

   RPMS will be populated in $HOME/rpmbuild/RPMS.
   
6. Execute tests from manual-tests folder to test the patches.
   Firt, unpack the following RPMs under $HOME/rpmbuild/RPMS.
   
```
gtk3
gtk3-immodules
gtk3-immodule-xim
gtk-update-icon-cache
```   
   Then run the test apps with environment variables set as follows:
   
```
gtk_usr_dir="$HOME/rpmbuild/RPMS/usr"
export LD_LIBRARY_PATH="${gtk_usr_dir}/lib64:${LD_LIBRARY_PATH}"
    
# Specify path to printbackends and immodules
export GTK_PATH="${gtk_usr_dir}/lib64/gtk-3.0/3.0.0"

export PATH="${gtk_usr_dir}/bin:${PATH}"
```

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

3. Download wayland-protocols-devel-1.14-1 RPM from https://vault.centos.org/centos/7.5.1804/cr/x86_64/Packages/wayland-protocols-devel-1.14-1.el7.noarch.rpm and unpack it somewhere.

4. Copy wayland-protocols-devel-1.14-1.el7.noarch/usr/share/wayland-protocols/stable/xdg-shell/xdg-shell.xml to /usr/share/wayland-protocols/stable/xdg-shell/xdg-shell.xml


See also https://docs.gtk.org/gtk3/building.html
