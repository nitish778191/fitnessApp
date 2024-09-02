      <tbody>
        <template x-for="hunt in filteredHunts" :key="hunt.id">
          <tr class="text-lseg-darkgrey text-sm">
            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
              <a 
                class="py-4 px-4 hover:text-lseg-blue hover:underline"
                :href="`/${hunt.id.toLowerCase()}`"
                x-text="hunt.id"
              ></a>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.hunt_ticket"></span>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <div class="flex flex-wrap gap-4">
                <template x-for="item in hunt.data.attack_coverage" :key="item.technique">
                  <span>
                    <a
                      :title="getTechniqueName(item.technique)"
                      class="hover:underline hover:text-lseg-blue"
                      :href="`${baseURL}/technique/${item.technique}`"
                      x-text="item.technique"
                    ></a>
                    <template x-for="st in item.subtechniques" :key="st">
                      <a
                        class="hover:underline hover:text-lseg-blue"
                        :title="getTechniqueName(st)"
                        :href="`${baseURL}/technique/${st}`"
                        x-text="st"
                      ></a>
                    </template>
                  </span>
                </template>
              </div>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <span x-text="hunt.data.title"></span>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.platform"></span>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.creation_date"></span>
            </td>
          </tr>
        </template>
      </tbody>
