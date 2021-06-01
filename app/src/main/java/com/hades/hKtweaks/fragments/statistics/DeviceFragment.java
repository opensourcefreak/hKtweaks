/*
 * Copyright (C) 2015-2016 Willi Ye <williye97@gmail.com>
 *
 * This file is part of Kernel Adiutor.
 *
 * Kernel Adiutor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kernel Adiutor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kernel Adiutor.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.hades.hKtweaks.fragments.statistics;

import com.hades.hKtweaks.R;
import com.hades.hKtweaks.fragments.DescriptionFragment;
import com.hades.hKtweaks.fragments.recyclerview.RecyclerViewFragment;
import com.hades.hKtweaks.utils.AppSettings;
import com.hades.hKtweaks.utils.kernel.battery.Battery;
import com.hades.hKtweaks.utils.Device;
import com.hades.hKtweaks.utils.kernel.gpu.GPUFreqExynos;
import com.hades.hKtweaks.views.recyclerview.CardView;
import com.hades.hKtweaks.views.recyclerview.DescriptionView;
import com.hades.hKtweaks.views.recyclerview.RecyclerViewItem;

import java.util.List;

/**
 * Created by willi on 28.04.16.
 */
public class DeviceFragment extends RecyclerViewFragment {

    @Override
    protected void init() {
        super.init();

        String vendor = Device.getVendor();
        vendor = vendor.substring(0, 1).toUpperCase() + vendor.substring(1);

        addViewPagerFragment(DescriptionFragment.newInstance(vendor + " " + Device.getModel(),
                Device.getBoard().toUpperCase()));
    }

    @Override
    protected void addItems(List<RecyclerViewItem> items) {
        String[][] swInfo = {
                {getString(R.string.android_version), Device.getVersion()},
                {getString(R.string.android_api_level), String.valueOf(Device.getSDK())},
                {getString(R.string.android_codename), Device.getCodename()},
                {getString(R.string.bootloader), Device.getBootloader()},
                {getString(R.string.baseband), Device.getBaseBand()},
                {getString(R.string.build_display_id), Device.getBuildDisplayId()},
                {getString(R.string.fingerprint), Device.getFingerprint()},
                {getString(R.string.kernel), Device.getKernelVersion(true)},
                {"GPU " + getString(R.string.gpu_driver_version), GPUFreqExynos.getInstance().getDriverVersion()},
                {"GPU " + getString(R.string.gpu_lib_version), AppSettings.getString("gpu_lib_version", "", getActivity())},
                {getString(R.string.rom), Device.ROMInfo.getInstance().getVersion()},
                {getString(R.string.trustzone), Device.TrustZone.getInstance().getVersion()}
        };
        String[][] hwInfo = {
                {getString(R.string.manufactured_date), Device.getManufacturedDate()},
                {getString(R.string.hardware), Device.getHardware()},
                {getString(R.string.architecture), Device.getArchitecture()},
                {"CPU cores", getString(Device.getCoreCount() > 1 ?
                        R.string.cores : R.string.cores_singular, Device.getCoreCount())},
                {getString(R.string.ram), (int) Device.MemInfo.getInstance().getTotalMem() + getString(R.string.mb)},
                {getString(R.string.uptime), Device.getUptime()},
                {getString(R.string.battery_health), Battery.getHealthValue()+"%"},
                {getString(R.string.asv), Device.getAsv()},
                {getString(R.string.cpu_features), Device.CPUInfo.getInstance().getFeatures()}
        };

        CardView swCard = new CardView(getActivity());
        swCard.setTitle(getString(R.string.swInfo));

        CardView hwCard = new CardView(getActivity());
        hwCard.setTitle(getString(R.string.hwInfo));

        for (String[] softwareInfo : swInfo) {
            if (softwareInfo[1] != null && softwareInfo[1].isEmpty()) {
                continue;
            }
            DescriptionView info = new DescriptionView();
            info.setTitle(softwareInfo[0]);
            info.setSummary(softwareInfo[1]);
            swCard.addItem(info);
        }

        for (String[] hardwareInfo : hwInfo) {
            if (hardwareInfo[1] != null && hardwareInfo[1].isEmpty()) {
                continue;
            }
            DescriptionView info = new DescriptionView();
            info.setTitle(hardwareInfo[0]);
            info.setSummary(hardwareInfo[1]);
            hwCard.addItem(info);
        }

        items.add(swCard);
        items.add(hwCard);
    }
}
